(ns aoc-2021.day5
  (:require [clojure.string :as str]))

(defn- sign [x]
  (cond
    (< x 0) -1
    (> x 0) 1
    :otherwise 0))

(defn- points-between-straight [[x1 y1 x2 y2]]
  (let [dx (sign (- x2 x1))
        dy (sign (- y2 y1))]
    (loop [x x1
           y y1
           akk []]
      (if (and (= x x2) (= y y2))
        (conj akk [x y])
        (recur (+ dx x) (+ dy y) (conj akk [x y]))))))

(defn- straight? [[x1 y1 x2 y2]]
  (or
    (= x1 x2) 
    (= y1 y2))) 

(defn- intersections [lines]
  (count 
    (filter 
      #(> (second %) 1) 
      (frequencies 
        (apply concat 
          (map points-between-straight lines))))))

(defn part1 [lines]
  (intersections (filter straight? lines)))

(defn part2 [lines]
  (intersections lines))

(defn- parse-line [line]
  (map #(Integer. %) (str/split line #"\D+")))

(def lines (map parse-line (str/split-lines (slurp "resources/day5_input.txt"))))

(println "Day 5 - 1: " (part1 lines)) ; 6856
(println "Day 5 - 2: " (part2 lines)) ; 20666
