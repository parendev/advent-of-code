(ns advent-of-code.year-2023.day02
  (:require [advent-of-code.year-2023.day-02.part1a :as part1]
            [advent-of-code.year-2023.day-02.part2a :as part2]
            [advent-of-code.test-utils.resource-mock :refer [mock-resources-fixture mock-resources]]
            [clojure.test :refer :all]))

(use-fixtures :each mock-resources-fixture)

(def PART_SAMPLE_INPUT "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\nGame 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\nGame 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\nGame 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
(def PART1_SAMPLE_OUTPUT 8)
(def PART2_SAMPLE_OUTPUT 2286)

(deftest ^:2023 year-2023-day-02
  (testing "year_2023"
    (testing "day02"
      (testing "part1"
        (mock-resources {"year_2023/day02/input.txt" PART_SAMPLE_INPUT}
          (is (= (part1/-main) PART1_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input.")))
      (testing "part2"
        (mock-resources {"year_2023/day02/input.txt" PART_SAMPLE_INPUT}
          (is (= (part2/-main) PART2_SAMPLE_OUTPUT)
              "Returns correct result for puzzle's sample input."))))))
