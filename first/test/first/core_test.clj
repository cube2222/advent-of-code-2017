(ns first.core-test
  (:require [clojure.test :refer :all]
            [first.core :refer :all]))

(deftest get-sum-part1-test
  (testing (are [result input] (= result (get-sum-part1 input))
                               3 "1122"
                               4 "1111"
                               0 "1234"
                               9 "91212129")))

(deftest get-sum-part2-test
  (testing (are [result input] (= result (get-sum-part2 input))
                               6 "1212"
                               0 "1221"
                               4 "123425"
                               12 "123123"
                               4 "12131415")))
