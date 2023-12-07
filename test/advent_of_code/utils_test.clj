(ns advent-of-code.utils-test
  (:require [advent-of-code.utils :as utils]
            [clojure.test :refer :all])
  (:import (clojure.lang ArityException)))

(defn- assert-vector [result]
  (is (vector? result) "returns a vector"))

(deftest vector-add-and-subtract
  (testing "arity 0"
    (is (thrown? ArityException (utils/vector-add))
        "Cannot add zero vectors")
    (is (thrown? ArityException (utils/vector-subtract))
        "Cannot subtract zero vectors"))
  (testing "arity 1"
    (let [result (utils/vector-add utils/NORTH)]
      (assert-vector result)
      (is (= result utils/NORTH)
          "vector-add with only one argument returns that value"))
    (let [result (utils/vector-subtract utils/SOUTHWEST)]
      (assert-vector result)
      (is (= result utils/NORTHEAST)
          "vector-subtract with only one argument negates that argument")))
  (testing "arities 2+"
    (let [vec-count (+ 2 (rand-int 99))
          add-result (apply utils/vector-add (repeat vec-count [1 1]))
          subtract-result (apply utils/vector-subtract (repeat vec-count [1 1]))
          expected-difference (apply - (repeat vec-count 1))]
      (assert-vector add-result)
      (is (= add-result [vec-count vec-count])
          (str "able to add an arbitrary number (" vec-count ") of vectors"))
      (assert-vector subtract-result)
      (is (= subtract-result [expected-difference expected-difference])
          (str "able to subtract an arbitrary number (" vec-count ") of vectors"))))
  (testing "any-dimensional"
    (let [n (rand-int 100)
          vec-a (range n)                                      ;[  0,   1,   2, ...,  n-1]
          vec-b (reverse vec-a)                                ;[n-1, n-2, n-3, ...,    0]
          add-result (utils/vector-add vec-a vec-b)            ;[n-1, n-1, n-1, ...,  n-1]
          subtract-result (utils/vector-subtract vec-a vec-b)] ;[1-n, 3-n, 5-n, ..., 2n-1]
      (assert-vector add-result)
      (is (= add-result (vec (repeat n (dec n)))) ;[n-1, n-1, n-1, ...,  n-1]
          (str "able to add vectors of arbitrary size (" n ")"))
      (assert-vector subtract-result)
      (is (= subtract-result (vec (take n (iterate #(+ 2 %) (- 1 n))))) ;[1-n, 3-n, 5-n, ..., 2n-1]
          (str "able to subtract vectors of arbitrary size (" n ")")))))

(deftest vector-scaling
  (testing "single multiplier"
    (let [n (rand-int 100)
          result (utils/vector-scale 10 (range 1 (inc n)))]
      (assert-vector result)
      (is (= result (vec (take n (iterate (partial + 10) 10))))
          "multiplies each item in vector by multiplier")))
  (testing "vector multiplier"
    (let [n (rand-int 100)
          result (utils/vector-scale (range 1 (inc n)) (range 1 (inc n)))]
      (assert-vector result)
      (is (= result (mapv #(* % %) (range 1 (inc n))))
          "multiplies each item in vector by item at same index in multiplier vector"))))
