(ns advent-of-code.year-2023.day-03.part1a
  (:require [advent-of-code.regex :as r]
            [advent-of-code.utils :as utils]))

(defn schematic-str->line-width
  "For a schematic string '...\n...\n' returns the length of each line (including line endings)"
  [s]
  (or (:end (r/re-find-details #"[\r\n]+" s))
      (count s)))

(def component->regex
  {:parts #"[^\.\d\r\n]"
   :part-nums #"\d+"})

(defn parse-schematic
  "Get a sequence of parts and part-nums from a schematic string, along with their locations in the schematic"
  [s]
  (let [line-width (schematic-str->line-width s)]
    (->> component->regex
         (utils/map-vals #(->> s
                               (r/re-seq-details %)
                               (map (fn [{:keys [match start]}]
                                      {:value match
                                       :coord [(mod start line-width) (quot start line-width)]}))))
         (into {}))))

(defn part-num->neighbor-coords
  "Given a part-num whose coord represents the location of its left-most digit, return the set of coordinates that
   neighbor any digit of the part-num, even diagonally"
  [{:keys [value coord]}]
  (let [top-neighbor-coords (->> (utils/vector-add utils/NORTHWEST coord) ;If part-num is at [0,0] start at [-1,-1]
                                 (iterate (partial utils/vector-add utils/EAST)) ;Sequence [-1,-1], [0,-1], [1,-1] ...
                                 (take (+ (count value) 2)))]
    (set (concat [(utils/vector-add coord utils/WEST) ; Coordinate for left neighbor
                  (->> utils/EAST
                       (utils/vector-scale (count value))
                       (utils/vector-add coord))] ; Coordinate of right neighbor
                 top-neighbor-coords
                 (map (partial utils/vector-add utils/SOUTH utils/SOUTH) top-neighbor-coords))))) ;Bottom neighbor coords

(defn -main []
  (let [input (utils/get-resource! "year_2023/day03/input.txt")
        {:keys [parts part-nums]} (parse-schematic input)
        output (->> part-nums
                    (filter (fn [part-num]
                              (let [is-neighbor (part-num->neighbor-coords part-num)]
                                (some (comp is-neighbor :coord) parts)))) ; Neighbor at least one part
                    (map (comp #(Integer/parseInt %) :value))
                    (apply +))]
    (println output)
    output))
