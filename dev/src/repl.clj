(ns repl
  (:require
    [cljs :refer :all]
    [clojure.tools.namespace.repl :refer [refresh refresh-all]]
    [kaocha.repl :as kr]
    [tick.core :as t]
    [tick.viz :refer [show-canvas view label]])
  (:import (java.util TimeZone)))

(set! *warn-on-reflection* true)

(defn set-zone [^String tz]
  (TimeZone/setDefault (TimeZone/getTimeZone tz))
  (alter-var-root #'t/*clock*
    (constantly (cljc.java-time.zone-id/system-default)))
  )

(comment
  
  (set-zone "America/New_York")
  
  )

(when (System/getProperty "nrepl.load")
  (require 'nrepl))

(defn test-clj* [] (kr/run :clj))

(defn test-clj []
  (refresh :after 'repl/test-clj*))

(comment
  (refresh-all)
  (clojure.tools.namespace.repl/clear)
  (test-clj)
  (cljs/figwheel-start!)
  (cljs/figwheel-stop!)
  (cljs/cljs-repl)
)
