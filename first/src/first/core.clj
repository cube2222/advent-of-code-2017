(ns first.core
  (:gen-class))

(defn string-to-int-seq
  [numbers]
  (let
    [nums (map #(Integer/parseInt (str %)) (seq numbers))]
    nums))

(defn to-pairs-with-offset
  [coll offset]
  (map vector coll (drop offset (cycle coll))))

(defn get-sum-with-offset
  [input offset]
  (let [numbers (string-to-int-seq input)
        pairs (to-pairs-with-offset numbers offset)
        good-ones (filter #(apply = %) pairs)
        single (map first good-ones)
        sum (reduce + single)]
    sum))

(defn get-sum-part1
  [input]
  (get-sum-with-offset input 1))

(defn get-sum-part2
  [input]
  (get-sum-with-offset input (/ (count input) 2)))