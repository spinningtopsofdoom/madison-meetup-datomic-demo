(defproject datomic-demo "0.0.1-SNAPSHOT"
  :description "Demostration of Datomic database"
  :source-paths ["src" ]
  :dependencies [[org.clojure/clojure "1.9.0-beta3"]
                 [com.datomic/datomic-free  "0.9.5561.62" ] ]

  :jvm-opts ^:replace ["-Xmx512m" "-server"]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"})
