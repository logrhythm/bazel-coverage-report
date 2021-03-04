# Copyright 2018 The Bazel Authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

workspace(name = "hchauvin_bazel_coverage_report")

load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

http_archive(
    name = "com_google_protobuf",
    sha256 = "f6600abeee3babfa18591961a0ff21e7db6a6d9ef82418a261ec4fee44ee6d44",
    strip_prefix = "protobuf-3.4.0",
    urls = ["https://github.com/google/protobuf/archive/v3.4.0.tar.gz"],
)

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "52d0a57ea12139d727883c2fef03597970b89f2cc2a05722c42d1d7d41ec065b",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.24.13/rules_go-v0.24.13.tar.gz",
        "https://github.com/bazelbuild/rules_go/releases/download/v0.24.13/rules_go-v0.24.13.tar.gz",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "222e49f034ca7a1d1231422cdb67066b885819885c356673cb1f72f748a3c9d4",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.22.3/bazel-gazelle-v0.22.3.tar.gz",
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.22.3/bazel-gazelle-v0.22.3.tar.gz",
    ],
)


load("@io_bazel_rules_go//go:def.bzl", "go_register_toolchains", "go_rules_dependencies")

go_rules_dependencies()

go_register_toolchains()

load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

gazelle_dependencies()

git_repository(
    name = "com_grail_rules_r",
    commit = "b817de35084b540cd9135f5a786044d83ff1dea5",
    remote = "https://github.com/hchauvin/rules_r.git",
)

load("@com_grail_rules_r//R:dependencies.bzl", "r_coverage_dependencies", "r_rules_dependencies")

r_rules_dependencies()

r_coverage_dependencies()

git_repository(
    name = "build_bazel_rules_nodejs",
    commit = "a879a9cde370775b0642776065d07904afcac466",
    remote = "https://github.com/hchauvin/rules_nodejs.git",
)

load("@build_bazel_rules_nodejs//:defs.bzl", "node_repositories")

# NOTE: this rule installs nodejs, npm, and yarn, but does NOT install
# your npm dependencies into your node_modules folder.
# You must still run the package manager to do this.
node_repositories(package_json = ["//:package.json"])

load("@build_bazel_rules_nodejs//:defs.bzl", "npm_install")

npm_install(
    name = "js_deps",
    package_json = "//:package.json",
)

git_repository(
    name = "build_bazel_rules_typescript",
    commit = "1cab5c4fc8618149068f8cd5b4382e3c2eff0bd0",
    remote = "https://github.com/hchauvin/rules_typescript.git",
)

load("@build_bazel_rules_typescript//:defs.bzl", "ts_setup_workspace")

ts_setup_workspace()

git_repository(
    name = "io_bazel_rules_kotlin",
    commit = "2d52faa1203390ebd1ec6a219b112dc7a1307a19",
    remote = "https://github.com/hchauvin/rules_kotlin.git",
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

kotlin_repositories(kotlin_release_version = "1.2.30")

kt_register_toolchains()

load("@bazel_tools//tools/build_defs/repo:java.bzl", "java_import_external")

java_import_external(
    name = "junit",
    jar_sha256 = "59721f0805e223d84b90677887d9ff567dc534d7c502ca903c0c2b17f05c116a",
    jar_urls = [
        "http://repo1.maven.org/maven2/junit/junit/4.12/junit-4.12.jar",
        "http://maven.ibiblio.org/maven2/junit/junit/4.12/junit-4.12.jar",
    ],
    licenses = ["reciprocal"],  # Eclipse Public License 1.0
    deps = ["@org_hamcrest_core"],
)

java_import_external(
    name = "org_hamcrest_core",
    jar_sha256 = "66fdef91e9739348df7a096aa384a5685f4e875584cce89386a7a47251c4d8e9",
    jar_urls = [
        "http://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar",
        "http://maven.ibiblio.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar",
    ],
    licenses = ["notice"],  # New BSD License
)

## Linting

load("//private:format.bzl", "format_repositories")

format_repositories()

git_repository(
    name = "io_bazel_rules_python",
    commit = "c741df316b9eab2c9160835398394c854e753b91",
    remote = "https://github.com/hchauvin/rules_python.git",
)

load("@io_bazel_rules_python//python:pip.bzl", "pip_import")

pip_import(
    name = "format_py_deps",
    requirements = "//private:requirements.txt",
)

load("@format_py_deps//:requirements.bzl", "pip_install")

pip_install()

## Report generator

load("//report:defs.bzl", "bazel_coverage_report_repositories")

bazel_coverage_report_repositories()
