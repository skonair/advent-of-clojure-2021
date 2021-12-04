(ns aoc-2021.day4
  (:require [clojure.string :as str
             clojure.set :as set]))

(defn- contains-all? [l c]
  (let [i (set/intersection (set l) (set c))]
    (= (count l) (count i))))

(defn- column-win? [numbers game]
  (some identity (map #(contains-all? % numbers) game)))

(defn- wins? [numbers game]
  (column-win? numbers (concat game (apply mapv vector game))))

(defn- difference [numbers game]
  (set/difference (set (flatten game)) (set numbers)))

(defn- run [numbers games ws]
  (loop [cnt 5
         lastWinner nil
         notWon (set games)]
    (let [nums (take cnt numbers)
          winners (filter #(wins? nums %) notWon)]
      (if 
        (= (count notWon) (- (count games) ws))
        (* (apply + (difference (drop-last nums) lastWinner)) (last (drop-last nums)))
        (recur (inc cnt) (first winners) (set/difference notWon (set winners)))))))

(defn- line-to-int-array [line]
  (map 
    #(Integer. (str/trim %))
    (str/split (str/triml line) #"\s+")))

(defn- parse-games [lines]
  (loop [[l & ls] lines
         game []
         games []]
    (if (nil? l)
      (conj games game)
      (if (empty? l)
        (recur ls [] (conj games game))
        (recur ls (vec (conj game (line-to-int-array l))) games)))))

(defn part1 [numbers games]
  (run numbers games 1))

(defn part2 [numbers games]
  (run numbers games (count games)))

(def lines (str/split-lines (slurp "resources/day4_input.txt")))
(def numbers (map #(Integer. %) (str/split (first lines) #",")))
(def games (parse-games (drop 2 lines)))

(println "Day 4 - 1: " (part1 numbers games)) ; 11536
(println "Day 4 - 2: " (part2 numbers games)) ; 1284
