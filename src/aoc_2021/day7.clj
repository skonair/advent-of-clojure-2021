(ns aoc-2021.day7
  (:require [clojure.string :as str]))


(defn- distance [n ivals]
  (apply 
    + 
    (map 
      #(Math/abs (- n %)) 
      ivals)))

(defn- distance2 [n ivals]
  (apply 
    + 
    (map 
      #(apply + (range (inc (Math/abs (- n %))))) 
      ivals)))

(defn part1 [input]
  (apply min (map #(distance % input) (range (apply max input)))))

(defn part2 [input]
  (apply min (map #(distance2 % input) (range (apply max input)))))

(def input (map #(Integer. %) [16,1,2,0,4,2,7,1,2,14]))
(def input (map #(Integer. %) (slurp "resources/day7_input.txt")))
(def input (map #(Integer. %) (str/split (first (str/split-lines (slurp "resources/day7_input.txt"))) #",")))

(println "Day 7 - 1: " (part1 input)) ; (distance 354 input) 342730
(println "Day 7 - 2: " (part2 input)) ; (distance2 476 input) 92335207
