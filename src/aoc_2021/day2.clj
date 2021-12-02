(ns aoc-2021.day2
  (:require [clojure.string :as str]))


(defn- execute [[cmd param] [x z]]
  (let [i (Integer. param)]
    (cond
      (= cmd "forward") [(+ x i) z]
      (= cmd "down") [x (+ z i)]
      (= cmd "up") [x (- z i)])))

(defn part1 [cmds]
  (loop [[c & cs] cmds
         [x z] [0 0]]
    (if
      (nil? c) (* x z)
      (recur cs (execute c [x z])))))

(defn- execute-aim [[cmd param] [aim x z]]
  (let [i (Integer. param)]
    (cond
      (= cmd "forward") [aim (+ x i) (+ z (* aim i))]
      (= cmd "down") [(+ aim i) x z]
      (= cmd "up") [(- aim i) x z])))


(defn part2 [cmds]
  (loop [[c & cs] cmds
         [aim x z] [0 0 0]]
    (if
      (nil? c) (* x z)
      (recur cs (execute-aim c [aim x z])))))

(def input (map #(str/split % #" ") (str/split-lines (slurp "resources/day2_input.txt"))))

(println "Day 2 - 1: " (part1 input)) ; 2073315
(println "Day 2 - 2: " (part2 input)) ; 1840311528
