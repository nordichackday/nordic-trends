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


@app.route('/')
def index():
    return 'Index'


@app.route('/tags')
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


def random_output():
    tags = []
    for i in range(10):
        tag = {
            "name" : str(uuid.uuid4())[0:5],
            "title": str(uuid.uuid4())[5:18],
            "uri"  : "http://www.example.com/{}".format(uuid.uuid4()),
            "num"  : random.randint(1, 100)
        }
        tags.append(tag)
    return tags


if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)
