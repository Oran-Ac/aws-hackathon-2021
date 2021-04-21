#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import json
import boto3
from urllib.parse import unquote_plus
import os
import sys
import uuid

LANGUAGES=['af','sq','am','ar','hy','az','bn','bs','bg','ca','zh','zh-TW','hr','cs','da','fa-AF','nl','en','et','fa','tl','fi','fr','fr-CA','ka','de','el','gu','ht','ha','he','hi',
            'hu','is','id','it','ja','kn','kk','ko','lv','lt','mk','ms','ml','mt','mn','no','fa','ps','pl','pt','ro','ru','sr','si','sk','sl','so','es','es-MX','sw','sv','tl','ta','te','th',
            'tr','uk','ur','uz','vi','cy']
def fn_translate(translate,source,target,text):
    return translate.translate_text(Text=text,SourceLanguageCode=source, TargetLanguageCode=target).get('TranslatedText') 

def clean_data(context):
    pass
    
def clear(bucket,key):
    s3 = boto3.resource('s3')
    s3.Object(bucket, key).delete()

def translate(input_file,output_file):
    origin = ''
    with open(input_file,'r') as f:
        origin = json.loads(f.read())
    
    translate = boto3.client(service_name='translate')

    for lang in LANGUAGES:
        trans = {}
        for k in origin.keys():
            trans[k] = fn_translate(translate,'auto',lang,origin[k])
            
        with open(f'{output_file}/client_{lang}.json','w') as f:
            f.write(json.dumps(trans,ensure_ascii=False))    
        
        # if lang.startswith('zh'):
        #     with open(f'{output_file}/client_{lang}.json','w',encoding='utf-8') as f:
        #         f.write(json.dumps(trans,ensure_ascii=False))
        # else:
        #     with open(f'{output_file}/client_{lang}.json','w') as f: 
        #         f.write(json.dumps(trans))
        
    
def lambda_handler(event, context):
    for record in event['Records']:
        bucket = record['s3']['bucket']['name']
        key = unquote_plus(record['s3']['object']['key']) #解码key值  balalla.json
        tmpkey = key.replace('/', '')
        #return [bucket,key]
        download_path = '/tmp/{}{}'.format(uuid.uuid4(), tmpkey) #生成临时文件与文件夹 uuid 随机数
        upload_path = '/tmp/translated' #生成零时文件
        
        if not os.path.exists(upload_path):
            os.makedirs(upload_path)
        s3_client = boto3.client('s3')
        s3_client.download_file(bucket,key, download_path)
    
    
    
        clear('{}-translated'.format(bucket),key) #删除translated原来的内容
        translate(download_path,upload_path)#翻译内容
        for lang in LANGUAGES:
            s3_client.upload_file(f'{upload_path}/client_{lang}.json', '{}-translated'.format(bucket), f'client_{lang}.json')#更新内容
   
    

