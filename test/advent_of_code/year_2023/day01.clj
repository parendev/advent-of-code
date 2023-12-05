(ns advent-of-code.year-2023.day01
  (:require [advent-of-code.year-2023.day-01.part1a :as part1]
            [advent-of-code.year-2023.day-01.part2a :as part2]
            [advent-of-code.test-utils.resource-mock :refer [mock-resources-fixture mock-resources]]
            [clojure.test :refer :all]))

(use-fixtures :each mock-resources-fixture)

(def PART1_SAMPLE_INPUT "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet")
(def PART1_SAMPLE_OUTPUT 142)

(def PART2_SAMPLE_INPUT "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen")
(def PART2_SAMPLE_OUTPUT 281)

(deftest ^:2023 year-2023-day-01
  (testing "year_2023"
    (testing "day01"
      (testing "part1"
        (mock-resources {"year_2023/day01/input.txt" PART1_SAMPLE_INPUT}
          (is (= (part1/-main) PART1_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input.")))
      (testing "part2"
        (mock-resources {"year_2023/day01/input.txt" PART2_SAMPLE_INPUT}
          (is (= (part2/-main) PART2_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input."))))))
