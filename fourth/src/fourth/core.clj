(ns fourth.core
  (:require [clojure.string :as str]))

(defn valid-1
  [words]
  (= (count words) (count (distinct words))))

(defn valid-2
  [words]
  (= (count words) (count (distinct (map sort words)))))

(defn solve
  "I don't do a whole lot."
  [validator input]
  (let [lines (str/split input #"\n")
        word-lines (map #(str/split % #"\s") lines)
        are-valid (map validator word-lines)
        numbers (map #(if %1 1 0) are-valid)
        valid-count (reduce + numbers)]
    valid-count))
