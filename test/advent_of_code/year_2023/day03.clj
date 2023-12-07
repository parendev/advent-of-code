(ns advent-of-code.year-2023.day03
  (:require [advent-of-code.year-2023.day-03.part1a :as part1]
            [advent-of-code.year-2023.day-03.part2a :as part2]
            [advent-of-code.test-utils.resource-mock :refer [mock-resources-fixture mock-resources]]
            [clojure.test :refer :all]))

(use-fixtures :each mock-resources-fixture)

(def PART_SAMPLE_INPUT "467..114..\n...*......\n..35..633.\n......#...\n617*......\n.....+.58.\n..592.....\n......755.\n...$.*....\n.664.598..")
(def PART1_SAMPLE_OUTPUT 4361)
(def PART2_SAMPLE_OUTPUT 467835)

(deftest ^:2023 year-2023-day-02
  (testing "year_2023"
    (testing "day02"
      (testing "part1"
        (mock-resources {"year_2023/day03/input.txt" PART_SAMPLE_INPUT}
          (is (= (part1/-main) PART1_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input.")))
      (testing "part2"
        (mock-resources {"year_2023/day03/input.txt" PART_SAMPLE_INPUT}
          (is (= (part2/-main) PART2_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input."))))))
