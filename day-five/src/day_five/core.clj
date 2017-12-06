(ns day-five.core
  (:require [clojure.string :as str]))

(defn advance-state-1
  [{:keys [instructions position] :as state}]
  (let [instruction (nth instructions position)
        new-instructions (assoc instructions position (+ instruction 1))
        new-position (+ position instruction)]
    (-> state
        (assoc :instructions new-instructions)
        (assoc :position new-position))))

(defn advance-state-2
  [{:keys [instructions position] :as state}]
  (let [instruction (nth instructions position)
        new-instructions (if (>= instruction 3)
                           (assoc instructions position (- instruction 1))
                           (assoc instructions position (+ instruction 1)))
        new-position (+ position instruction)]
    (-> state
        (assoc :instructions new-instructions)
        (assoc :position new-position))))

(defn foo
  "I don't do a whole lot."
  [state-advancer input]
  (let [numbers (->> input
                     (str/split-lines)
                     (map #(Integer/parseInt %))
                     (into []))
        start-state {:instructions numbers
                     :position 0
                     :size (count numbers)}
        tape (->> (iterate state-advancer start-state)
                  (map vector (iterate #(+ % 1) 0)))]
    (first
      (drop-while
        (fn [[step state]] (< (:position state) (:size state)))
        tape))))
