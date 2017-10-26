import requests
import json

albums=[
    {"name":"Singularity","id":"1","artist":"Stortregn"},
    {"name":"Trollmin","id":"2","artist":"Drakum"},
    {"name":"Evocation Of Light","id":"3","artist":"Stortregn"},
    {"name":"Uncreation","id":"4","artist":"Stortregn"},
    {"name":"Devoured by Oblivion","id":"5","artist":"Stortregn"}
]

artist=[
    {"name":"Stortregn","id":"1","albumIds":["1","3","4","5"]},
    {"name":"Drakum","id":"2","albumIds":["2"]}
]

for a in albums:
    r=requests.post("http://192.168.99.100:8080/v1/albums",json=a)
    if(r.status_code != 204):
        print("problem with "+str(a))
        print (r.status_code)

for a in artist:
    r=requests.post("http://192.168.99.100:8081/v1/artists",json=a)
    if(r.status_code != 204):
        print("problem with "+str(a))
        print (r.status_code)