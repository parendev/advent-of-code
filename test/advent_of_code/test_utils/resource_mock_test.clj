(ns advent-of-code.test-utils.resource-mock-test
  (:require [advent-of-code.test-utils.resource-mock :as rm]
            [advent-of-code.utils :as utils]
            [clojure.test :refer :all])
  (:import (clojure.lang ExceptionInfo)))

(use-fixtures :each rm/mock-resources-fixture)

(deftest ^:general resource-mock
  (testing "resource-mock"
    (rm/mock-resources {"foo" "bar"}
      (is (= (utils/get-resource! "foo") "bar")
          "Returns mocked resource")
      (is (thrown-with-msg? ExceptionInfo (re-pattern rm/ACCESS_UNMOCKED_RESOURCE_MESSAGE)
                            (utils/get-resource! "unmocked-path"))
          "Throws if accessing unmocked resource"))))
