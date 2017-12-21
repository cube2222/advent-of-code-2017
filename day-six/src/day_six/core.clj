(ns day-six.core
  (:require [clojure.string :as str]))

(defn better
  [x y-k y-v]
  (if (> y-v (second x))
    [y-k y-v]
    x))

(defn reallocate
  [position banks]
  (let [left (banks position)
        new-banks (assoc banks position 0)
        size (count banks)]
    (loop [cur-position (mod (+ position 1) size)
           cur-banks new-banks
           cur-left left]
      (if (= 0 cur-left)
        cur-banks
        (let [new-banks (assoc
                          cur-banks
                          cur-position
                          (inc (cur-banks cur-position)))
              new-position (mod (+ cur-position 1) size)]
          (recur new-position
                 new-banks
                 (dec cur-left)))))))

(defn advance-state
  [banks]
  (let [position (first (reduce-kv better [-1 0] banks))]
    (reallocate position banks)))

(defn tape-next
  [{:keys [history cur-state] :as cur-tape}]
  (-> cur-tape
      (assoc :history (conj history cur-state))
      (assoc :cur-state (advance-state cur-state))))

(defn foo
  "I don't do a whole lot."
  [input]
  (let [banks (->> (str/split input #"\s")
                   (map #(Integer/parseInt %))
                   (into []))
        begin-state {:history #{} :cur-state banks}
        tape (iterate tape-next begin-state)
        tape-i (map vector tape (iterate inc 0))
        first-repeated (drop-while (fn [[tape i]]
                                     (not ((:history tape) (:cur-state tape))))
                                   tape-i)]
    (first first-repeated)))
