(ns advent-of-code.utils
  (:require [clojure.java.io :as io]))

(def get-resource! (comp slurp io/resource))
