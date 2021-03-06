// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.hchauvin.bazelcoverageexample;

import java.util.Arrays;
import java.util.List;

public class Foo {
  private Foo() {}

  public static String exampleA() {
    return "A";
  }

  public static String exampleB() {
    return "B";
  }

  public static List<String> exampleC() {
    return Arrays.asList(exampleA(), exampleB(), exampleB(), "C");
  }
}
