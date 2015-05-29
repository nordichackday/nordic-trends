# -*- coding: utf-8 -*-

"""
Cloudify the Nordic trends.

Like a boss.
"""
from flask import Flask, jsonify, request
from datetime import datetime
import urllib2
import json
import re
import random
import uuid
import os
import sys
from flask.ext.cors import CORS

app = Flask(__name__)
cors = CORS(app)

ORIGINS = ("Yle (sv)", "Yle (fi)", "SVT", "NRK", "DR", "RUV")


@app.route('/')
def index():
    return 'Index'

@app.route('/tags/<num_items>')
def tags(num_items):
    data = ""
    out = { "tags" : [] }
    jsn = load_source_data()

    for feed in jsn:
        tags = {}
        for article in feed['articles']:
            tags = taggify_words(tags, article['description'].encode('utf-8'))

            # Add titles and links
            for tag, num in tags.iteritems():
                if num < int(num_items): continue
                out['tags'].append({
                    "name" : tag,
                    "num": num,
                    "origin": feed['source'],
                    "title": article['title'],
                    "uri": article['url']
                    })

    out['tags'] = gen_rel_float_value(out['tags'])
    return jsonify(out)

@app.route('/random')
def random_endpoint():
    tags = random_output()
    tags = gen_rel_float_value(tags)
    data = { "tags": tags }

    return jsonify(data)


def gen_rel_float_value(tags):
    maxval = 0
    for tag in tags:
        if tag['num'] > maxval:
            maxval = tag['num']
    out = []
    for tag in tags:
        tag['relnum'] = float(tag['num']) / maxval
        out.append(tag)
    return out


def taggify_words(tags, words):
    for word in words.split(" "):
        w = re.sub(r"[^\w]", '', word)
        if w == "": continue
        if w in tags:
            tags[w] = tags[w] + 1
        else:
            tags[w] = 1
    return tags


def load_source_data():
    api_url = os.getenv("API_URL")
    jd = urllib2.urlopen(api_url).read()
    return json.loads(jd, encoding="utf-8")


def random_output():
    tags = []
    for i in range(30):
        tag = {
            "name" : str(uuid.uuid4())[0:5],
            "sources": [
                {
                    "title": str(uuid.uuid4())[5:18] + str(1),
                    "uri": "http://www.example.com/{}".format(uuid.uuid4())
                },{
                    "title": str(uuid.uuid4())[5:18] + str(2),
                    "uri": "http://www.example.com/{}".format(uuid.uuid4())
                },
            ],
            "num"  : random.randint(1, 10),
            "origin": ORIGINS[random.randint(0, len(ORIGINS) - 1)]
        }
        tags.append(tag)
    return tags


if __name__ == '__main__':
    if not os.getenv("API_URL"):
        print "Error, you need to spec the env variable API_URL"
        print "Example: API_URL=http://127.0.0.1:8080/articles"
        sys.exit(1)

    app.run(host='0.0.0.0',debug=True)
