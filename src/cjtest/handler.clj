(ns cjtest.handler
  (:use compojure.core
  		hiccup.core
  		selmer.parser)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn myhello [req]
	(html [:p "Hello there, "
			(get (get req :route-params) :name)]))

(defn templated [req]
	(render "Hello to templated {{name}}!"
		{:name (get (get req :route-params) :name)}))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/hello/:name" [name] (str "Hello <b>" name "</b>"))
  (GET "/myhello/:name" [name] myhello)
  (GET "/templated/:name" [name] templated)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
