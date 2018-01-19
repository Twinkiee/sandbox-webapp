<!DOCTYPE html>
<!--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
-->
<html>
<head>
  <title>Welcome</title>
  <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
<div class="container">
  <table class="table table-striped">
    <caption>Your todos are</caption>
    <thead>
    <tr>
      <th>Description</th>
      <th>Target Date</th>
      <th>Is it Done?</th>
      <th>Edit</th>
      <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Todo 1</td>
      <td>10/12/2017</td>
      <td>No</td>
      <td><a class="btn btn-warning" href="/edit-todo">Edit Todo</a></td>
      <td><a class="btn btn-warning" href="/delete-todo">Delete Todo</a></td>
    </tr>
    </tbody>
  </table>
  <div>
    <a class="btn btn-default" href="/add-todo">Add a Todo</a>
  </div>
  <script src="/webjars/jquery/1.9.1/jquery.min.js"></script>
  <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
</body>
</html>