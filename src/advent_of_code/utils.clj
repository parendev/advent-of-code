(ns advent-of-code.utils
  (:require [clojure.java.io :as io]))

(def get-resource! (comp slurp io/resource))

(defn map-vals [f coll]
  (map (fn [[k v]] [k (f v)]) coll))

(def NORTH [0 -1])
(def SOUTH [0 1])
(def WEST [-1 0])
(def EAST [1 0])
(def NORTHWEST [-1 -1])
(def NORTHEAST [1 -1])
(def SOUTHWEST [-1 1])
(def SOUTHEAST [1 1])

(def vector-add (partial mapv +))
(def vector-subtract (partial mapv -))
(defn vector-scale [multiplier coll]
  (if (number? multiplier)
    (mapv (partial * multiplier) coll)
    (mapv * multiplier coll)))
