(ns scraping-talk.skyscraper
  (:require [skyscraper :refer :all]
            [net.cgrand.enlive-html :refer :all]))


(defprocessor hn-index
  :cache-template "hn/index"
  :process-fn (fn [resource ctx]
                (for [row (select resource {[:tr.athing] [:tr.spacer]})]
                  (let [a-thing (select row [:tr.athing])
                        subtext (select row [:td.subtext])]
                    {:score (first (select subtext [:span.score]))
                     :title (first (select a-thing [:td.title :> :a text-node]))}))))

(defn seed [& _]
  [{:url "https://news.ycombinator.com"
    :processor :hn-index}])


(comment

  (scrape (seed) :processed-cache false)

  )
