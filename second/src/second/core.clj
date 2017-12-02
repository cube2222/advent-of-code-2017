(ns second.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn string-to-int-seq
  [numbers]
  (let
    [nums (map #(Integer/parseInt (str %)) (seq numbers))]
    nums))

(defn max-min
  [seq]
  (- (reduce max seq) (reduce min seq)))

(defn even-division
  [seq]
  (let [seqs (map #(vector seq %) seq)
        without-divisor (map (fn [[numbers divisor]]
                               (vector
                                 (filter
                                   #(not (= % divisor))
                                   numbers)
                                 divisor))
                             seqs)
        divided (mapcat
                  (fn [[numbers divisor]]
                    (map
                      #(/ % divisor)
                      numbers))
                  without-divisor)
        integers (filter integer? divided)]
    (first integers)))

(defn checksum
  [f input]
  (let [lines (str/split input #"\n")
        numbers (map #(str/split % #"\s") lines)
        int-seqs (map string-to-int-seq numbers)
        to-sum (map f int-seqs)
        sum (reduce + to-sum)]
    sum))