(ns aoc-2021.day6
  (:require [clojure.string :as str]))


(defn add-pops [ps]
  (repeat 
    (count (filter zero? ps))
    8))

(defn- decr-pops [ps]
  (map #(if (zero? %) 6 (dec %)) ps))

(defn- new-child [round]
  (println "new-child " round)
  (let [nn (- round 9)]
    (println "nn " nn)
    (inc (childs nn))))

(defn- childs [rounds]
  (if (< rounds 7)
    0 
    (let [childs (for [a (range (quot rounds 7))] (- rounds (* (inc a) 7)))]
      (println childs)
      (apply + (count childs) (map new-child childs))))) 

(defn- start [n]
  (inc (childs (- 17 n)))

; brute force approach
(defn- run [ps max-runs]
  (loop [cnt 0
         pops ps]
    (println cnt)
    (if (= cnt max-runs)
      (count pops)
      (let [new-pops (add-pops pops)
            dec-pops (decr-pops pops)]
        (recur (inc cnt) (concat dec-pops new-pops))))))


(defn part1 [input]
  (run input 80))

(defn part2 []
)

(def lines (map #(Long. %) (str/split (first (str/split-lines (slurp "resources/day6_input.txt"))) #",")))


(println "Day 6 - 1: " (part1 input)) ; 394994
(println "Day 6 - 2: " (part2 input))
