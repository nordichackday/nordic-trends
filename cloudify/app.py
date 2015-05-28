from flask import Flask, jsonify, request
from datetime import datetime
import urllib2
import json
import re

app = Flask(__name__)

@app.route('/')
def index():
    return 'Index'

@app.route('/data') #methods=['POST'])
def names():
    return jsonify([])

if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)
