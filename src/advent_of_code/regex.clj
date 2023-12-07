(ns advent-of-code.regex
  (:import (java.util.regex Matcher Pattern)))

(defn- get-match-details
  "Assumes prior find or match operation was successful"
  [^Matcher m]
  (let [gc (.groupCount m)]
    {:match (.group m)
     :groups (when-not (zero? (.groupCount m))
               (loop [ret [] c 1]
                 (if (<= c gc)
                   (recur (conj ret (. m (group c))) (inc c))
                   ret)))
     :start (.start m)
     :end (.end m)}))

(defn re-find-details
  "Returns next substring matching regex pattern `re`, as per java.util.regex.Matcher.find(), as a map where
   :match is the matching substring, :groups are any groups from the match, and :start and :end are the match indices.
   Returns nil if no match is found."
  ([^Matcher m]
   (when (.find m)
     (get-match-details m)))
  ([^Pattern re s]
   (re-find-details (re-matcher re s))))

(defn re-matches-details
  "Returns input string if it matches regex pattern `re`, as per java.util.regex.Matcher.matches(), as a map where
   :match is the input string, :groups are any groups from the match, and :start and :end are the match indices.
   Returns nil if string doesn't match."
  ([^Matcher m]
   (when (.matches m)
     (get-match-details m)))
  ([^Pattern re s]
   (re-matches-details (re-matcher re s))))

(defn re-seq-details
  "Returns lazy sequence of substrings matching regex pattern `re`, as per repeated application of
   java.util.regex.Matcher.find(), as a map where :match is the matching substring, :groups are any groups from the
   match, and :start and :end are the match indices."
  ([^Matcher m]
   ((fn step []
      (lazy-seq
        (when-let [match (re-find-details m)]
          (cons match (step)))))))
  ([^Pattern re s]
   (re-seq-details (re-matcher re s))))
