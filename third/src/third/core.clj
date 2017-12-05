(ns third.core)

(defn part-1
  "I don't do a whole lot."
  [x]
  (let [sizes (iterate #(+ 2 %) 1)
        fields (map #(* % %) sizes)
        rings (map (fn [field ring]
                     {:field field :ring ring})
                   fields
                   (iterate #(+ 1 %) 0))
        sides (map #(assoc %1 :side (- (:field %1) %2)) rings (conj fields 0))
        offsets (map #(assoc %1 :offset (int (/ (:side %1) 4))) sides)
        cur-ring (first (drop-while
                          (fn [{:keys [field]}]
                            (> x field))
                          offsets))
        side-mids (take 4 (iterate
                            #(- % (:offset cur-ring))
                            (- (:field cur-ring) (:ring cur-ring))))
        best (apply min
                   (map
                     #(max (- %1 x) (- x %1))
                     side-mids))]
    (+ best (:ring cur-ring))))

