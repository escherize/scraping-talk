(ns scraping-talk.scrape
  (:require [net.cgrand.enlive-html :as html]
            [clojure.pprint :refer [pprint]]))

(let [resource (-> "resources/public/index.html"
                   slurp
                   java.io.StringReader.
                   html/html-resource)]
  (html/select resource [:title.scrape-me]))

;;=> [{:tag :title,
;;     :attrs {:class "scrape-me"},
;;     :content ["Interesting Thing"]}]
