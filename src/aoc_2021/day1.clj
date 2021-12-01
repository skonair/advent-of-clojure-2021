(ns aoc-2021.day1
  (:require [clojure.string :as str]))

(defn- tuple-weight [akk t]
  (if (< (first t) (last t)) 
    (inc akk) 
    akk))

(defn- count-increases [numbers]
  (reduce 
    tuple-weight 
    0 
    (partition 2 1 numbers))) 

(defn part1 [lines]
   (count-increases lines))

(defn part2 [lines]
  (count-increases 
    (map #(apply + %) (partition 3 1 lines))))


(def input (map #(Integer. %) (str/split-lines (slurp "resources/day1_input.txt"))))

(println "Day 1 - 1: " (part1 input)) ; 1451
(println "Day 1 - 2: " (part2 input)) ; 1395
