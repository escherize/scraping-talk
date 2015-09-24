(ns scraping-talk.enlive-hn
  (:require [net.cgrand.enlive-html :as html]
            [clojure.pprint :refer [pprint]]))

(def ^:dynamic *base-url* "https://news.ycombinator.com/")

(defn fetch-url [url] (html/html-resource (java.net.URL. url)))

(defn hn-headlines []
  (->> (html/select (fetch-url *base-url*) [:td.title :a])
       (map html/text)))

(defn hn-points []
  (->> (html/select (fetch-url *base-url*) [:td.subtext html/first-child])
       (map html/text)))

(defn headlines-and-points []
  (map (fn [p h] {:points p
                  :headline h})
       (hn-points)
       (hn-headlines)))

(comment

  (headlines-and-points)

  )
