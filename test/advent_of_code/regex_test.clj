(ns advent-of-code.regex-test
  (:require [advent-of-code.regex :as r]
            [clojure.test :refer :all])
  (:import (clojure.lang LazySeq)))

(defn- assert-match [actual expected & [{:keys [index]}]]
  (let [decorate-msg (fn [msg] (if index (str "  MATCH[" index "]: " msg)
                                         msg))]
    (is (= (:match actual) (:match expected))
        (decorate-msg "Returns match string"))
    (if (nil? (:groups expected))
      (is (nil? (:groups actual))
          (decorate-msg "Groups nil when capture groups not used"))
      (is (= (:groups actual) (:groups expected))
          (decorate-msg "Returns value for each capture group in the match")))
    (is (and (= (:start actual) (:start expected))
             (= (:end actual) (:end expected)))
        (decorate-msg "Returns correct indices for match"))))

(defn assert-matches [actual expected]
  (is (instance? LazySeq actual)
      "Returns lazy sequence")
  (is (= (count actual) (count expected))
      "Sequence contains correct number of matches")
  (doseq [i (range (count expected))]
    (assert-match (nth actual i) (nth expected i) {:index i})))

(deftest ^:general re-find-details-test
  (testing "w/o capture groups"
    (assert-match (r/re-find-details #"\d+" "abc 123")
                  {:match "123" :groups nil :start 4 :end 7}))
  (testing "w/ capture groups"
    (assert-match (r/re-find-details #"(\d)(\d)(\d)" "abc 123")
                  {:match "123" :groups ["1" "2" "3"] :start 4 :end 7}))
  (testing "no match"
    (is (nil? (r/re-find-details #"xyz" "abc 123"))
        "Returns nil when no match found")))

(deftest ^:general re-matches-details-test
  (testing "w/o capture groups"
    (assert-match (r/re-matches-details #"\d+" "123")
                  {:match "123" :groups nil :start 0 :end 3}))
  (testing "w/ capture groups"
    (assert-match (r/re-matches-details #"([a-z]+) (\d+)" "abc 123")
                  {:match "abc 123" :groups ["abc" "123"] :start 0 :end 7}))
  (testing "no match"
    (is (nil? (r/re-matches-details #"abc" "abc 123"))
        "Returns nil when no match found")))

(deftest ^:general re-seq-details-test
  (testing "w/o capture groups"
    (assert-matches (r/re-seq-details #"\d+" "123 456 789")
                    [{:match "123" :groups nil :start 0 :end 3}
                     {:match "456" :groups nil :start 4 :end 7}
                     {:match "789" :groups nil :start 8 :end 11}]))
  (testing "w/ capture groups"
    (assert-matches (r/re-seq-details #"(\d)(\d)(\d)" "123 456 789")
                    [{:match "123" :groups ["1" "2" "3"] :start 0 :end 3}
                     {:match "456" :groups ["4" "5" "6"] :start 4 :end 7}
                     {:match "789" :groups ["7" "8" "9"] :start 8 :end 11}]))
  (testing "no match"
    (let [match-seq (r/re-seq-details #"[a-z]+" "123 456 789")]
      (is (instance? LazySeq match-seq)
          "Returns lazy sequence")
      (is (empty? match-seq)
          "Returns no matches"))))
