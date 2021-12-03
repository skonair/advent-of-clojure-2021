(ns aoc-2021.day3
  (:require [clojure.string :as str]))


(defn- n-bit [n line]
  (Character/digit (nth line n) 10))

(defn- find-occurences [n lines]
  (sort-by second (frequencies (map #(nth % n) lines))))

(defn- find-bits-by-func [lines fct default]
  (loop [n 0
         akk []]
    (if (= n (count (first lines))) 
      (Long/parseLong (str/join akk) 2)
      (let [occs (find-occurences n lines)
            bit (if (apply = (map second occs)) default (first (fct occs)))]
        (recur (inc n) (conj akk bit))))))

(defn- reduce-list [lines fct default]
  (loop [rlines lines
         akk []]
    (if (= 1 (count rlines))
      (Long/parseLong (first (filter #(str/starts-with? % (str/join akk)) lines )) 2)
      (let [occs (find-occurences 0 rlines)
            bit (if (apply = (map second occs)) default (first (fct occs)))]
        (recur (map #(subs % 1) (filter #(= bit (first %)) rlines)) (conj akk bit))))))

(defn part1 [lines]
  (* (find-bits-by-func lines first \0) (find-bits-by-func lines last \1)))

(defn part2 [lines]
  (* (reduce-list lines first \0) (reduce-list lines last \1)))

(def input (str/split-lines (slurp "resources/day3_input.txt")))

(println "Day 3 - 1: " (part1 input)) ; 4160394
(println "Day 3 - 2: " (part2 input)) ; 4125600
