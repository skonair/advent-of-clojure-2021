(ns aoc-2021.day8
  (:require [clojure.string :as str]
            [clojure.set :as set]))


; 1  2  <-
; 2  5
; 3  5
; 4  4  <-
; 5  5
; 6  6
; 7  3  <-
; 8  7  <-
; 9  6
; 0  6

(defn- digits [line]
  (filter 
    #(some #{%} #{2 3 4 7}) 
    (map count (drop 11 (str/split line #" ")))))

(defn part1 [lines]
  (count (flatten (map digits lines))))

(defn- inputs [line] 
  (take 10 (str/split line #" ")))

(defn- by-count [n ins]
  (first (filter #(= n (count %)) ins)))

(defn- conts [n ds ins]
  (first (filter #(and (set/subset? (set ds) (set %)) (= n (count %))) ins)))


(defn part2 [line]
  (let [ins (inputs line)
        d1 (by-count 2 ins)
        d4 (by-count 4 ins)
        d7 (by-count 3 ins)
        d8 (by-count 7 ins)
        d3 (conts 5 d1 ins)
        d9 (conts 6 d3 ins)
        d2
        d5
        d6
        d0
        ]
)

(def lines (str/split-lines (slurp "resources/day8_input.txt")))

(println "Day 8 - 1: " (part1 input))
(println "Day 8 - 2: " (part2 input))
