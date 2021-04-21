#!/usr/bin/env python
# coding: utf-8

# In[1]:


import json

import requests
from bs4 import BeautifulSoup
import re

def get_data(url,from_currency,to_currency):
    head ={'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36'}
    req=requests.get(url=url,headers=head)
    soup = BeautifulSoup(req.text,'html.parser')
    body = soup.find_all(class_='op_exrate_result')
    data = body[0].text
    a=re.findall(r"\d+\.?\d*",data)
    return {from_currency.upper():a[0],to_currency.upper():a[1]}
    
def lambda_handler(event, context):
    from_currency = event['from_currency']
    to_currency = event['to_currency']
    search = from_currency.lower() + to_currency.lower()
    url =  f'https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd={search}&fenlei=256&rsv_pq=a30882da00053475&rsv_t=dd79XwshzhscXAJ3zjppOsC9KSt705ifOixMFK0TfvXtaQSWDE27eVyDQoA&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=12&rsv_sug1=12&rsv_sug7=101&rsv_sug2=0&rsv_btype=i&prefixsug=%25E4%25BA%25BA%25E6%25B0%2591%25E5%25B8%2581%25E7%25BE%258E%25E5%2585%2583&rsp=5&inputT=4476&rsv_sug4=4879'  # 美元

    return get_data(url,from_currency,to_currency)
    


# In[ ]:




