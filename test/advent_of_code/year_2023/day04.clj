(ns advent-of-code.year-2023.day04
  (:require [advent-of-code.year-2023.day-04.part1a :as part1]
            [advent-of-code.year-2023.day-04.part2a :as part2]
            [advent-of-code.test-utils.resource-mock :refer [mock-resources-fixture mock-resources]]
            [clojure.test :refer :all]))

(use-fixtures :each mock-resources-fixture)

(def PART_SAMPLE_INPUT "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\nCard 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\nCard 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\nCard 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\nCard 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\nCard 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")
(def PART1_SAMPLE_OUTPUT 13)
(def PART2_SAMPLE_OUTPUT 30)

(deftest ^:2023 year-2023-day-04
  (testing "year_2023"
    (testing "day04"
      (testing "part1"
        (mock-resources {"year_2023/day04/input.txt" PART_SAMPLE_INPUT}
          (is (= (part1/-main) PART1_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input.")))
      (testing "part2"
        (mock-resources {"year_2023/day04/input.txt" PART_SAMPLE_INPUT}
          (is (= (part2/-main) PART2_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input."))))))
