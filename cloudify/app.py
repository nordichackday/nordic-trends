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
from flask.ext.cors import CORS

app = Flask(__name__)
cors = CORS(app)

ORIGINS = ("Yle (sv)", "Yle (fi)", "SVT", "NRK", "DR", "RUV")


@app.route('/')
def index():
    data = ""
    out = []
    jsn = load_source_data()

    for feed in jsn:
        for article in feed['articles']:
            out = taggify_words(out, article['description'])

    return out


@app.route('/random')
def tags():
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


def taggify_words(lst, words):
    return lst


def load_source_data():
    station_url = "http://192.168.1.53:8080/articles"
    jd = urllib2.urlopen(station_url).read()
    return json.loads(jd)


def random_output():
    tags = []
    for i in range(30):
        tag = {
            "name" : str(uuid.uuid4())[0:5],
            "title": str(uuid.uuid4())[5:18],
            "uri"  : "http://www.example.com/{}".format(uuid.uuid4()),
            "num"  : random.randint(1, 10),
            "origin": ORIGINS[random.randint(0, len(ORIGINS) - 1)]
        }
        tags.append(tag)
    return tags


if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)
