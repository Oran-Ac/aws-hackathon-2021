#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import json
import boto3

LANGUAGES=['af','sq','am','ar','hy','az','bn','bs','bg','ca','zh','zh-TW','hr','cs','da','fa-AF','nl','en','et','fa','tl','fi','fr','fr-CA','ka','de','el','gu','ht','ha','he','hi',
            'hu','is','id','it','ja','kn','kk','ko','lv','lt','mk','ms','ml','mt','mn','no','fa','ps','pl','pt','ro','ru','sr','si','sk','sl','so','es','es-MX','sw','sv','tl','ta','te','th',
            'tr','uk','ur','uz','vi','cy']

def fn_translate(translate,source,target,text):
    return translate.translate_text(Text=text,SourceLanguageCode=source, TargetLanguageCode=target).get('TranslatedText')

# def clean_data(event):
#     return event['Records'][0]['Sns']['Message']
    

def lambda_handler(event, context):
    '''
    when data in DB changed, the function provides the traslations of the origin discriptions
    
    :param event: The event dict that contains the parameters sent when the function
                  is invoked.
                  
    :param context: The context in which the function is called.
    
    :return: The translations of the origin and insert it into db_3
    
    
    '''
    """
    V1： envent = {
        source_language: 
        name:'',
        ~~price:'',~~:去掉这个的计算，这个应该是实时计算的，由java响应客户端时计算
        discriptions: ''
    }
    
    V2： event is a sns message
    """
    # Version1: translate it into all versions of languages
    # Version2:  handle many translations with sns
    
    translate = boto3.client(service_name='translate')
    print("called successfully")
    # event = clean_data(event)
    source_language = event['source_language'].strip()
    name =event['name'].strip()
    discriptions = event['discriptions'].strip()
    # res = []
    # for lang in LANGUAGES:
    #     print(lang)
    #     if lang == source_language:
    #         data ={
    #         'language': source_language,
    #         'name':name,
    #         'discriptions':discriptions
    #         }
    #     else:
    #         data={
    #             'language':lang,
    #             'name':fn_translate(translate,source_language,lang,name),
    #             'discriptions':fn_translate(translate,source_language,lang,discriptions)
    #         }
    #     res.append(data)
    data={
        'language':lang,
        'name':fn_translate(translate,source_language,lang,name),
        'discriptions':fn_translate(translate,source_language,lang,discriptions)
    }
    return data
        
    

