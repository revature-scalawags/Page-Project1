# Movie Answers
CLI application built to use Hive for querying a Yarn cluster running on a local container to answer 4 interesting questions about popular and not so popular movies. 

- - - -

>## Table of contents
>* [Description](#description)
>* [Screen Grab](#screen)
>* [Tech Used](#tech)
>* [Usage](#usage)
>* [Project Info](#project)
>* [Issues](#known-issues)



## Description
GroupLens Research has collected and made available their ratings datasets available at their  ___[MovieLens Website](http://movielens.org)___.  The MovieLens 25M movie ratings stable benchmark dataset describes 5-star ratings and free-text tagging activity. 25,000,095 ratings and 1,093,360 tag applications are applied to 62,423 movies by 162,541 users. It includes tag genome data with 15 million relevance scores across 1,129 tags. The data was generated between January of 1995 and November of 2019.  Released 11/2019.

This application uses Hive ontop of a Yarn cluster to query the MovieLens dataset and answers the following questions:
* What are the most popular movies ever?
* What are the 'worst' popular movies?
* What are some good however, unpopular movies?
* What movies correlate closely to their tag descriptions?

## Screen:
![company-drectory-optimize](https://user-images.githubusercontent.com/48693333/104199439-716d1800-53f5-11eb-862e-0572026c7fba.png)

## Tech Used and Required
+ Scala and SBT: https://www.scala-lang.org/download/2.12.8.html            
+ JDK (v11): https://jdk.java.net/15/               
+ Hive-jdbc driver via library dependency: v3.1.2

## Usage
These datasets can be acquired from *[movielens](https://grouplens.org/datasets/movielens/)*.           
the dataset used was their *25M Dataset*. The README for this data can be viewed *[here](http://files.grouplens.org/datasets/movielens/ml-25m-README.html)*.          
<details>
    __<summary>Files loaded into your hdfs:</summary>__
    -<p>ratings.csv</p>   
    -<p>tags.csv</p>    
    -<p>genome-scores.csv (*rqd for question 4 only*)</p>    
    -<p>genome-tags.csv (*rqd for question 4 only*)</p>     
</details>      

## Project:
[Repo](https://github.com/revature-scalawags/Page-Project1)    
[My Github](https://github.com/drthisguy)    
Email: page.c.tyler@gmail.com       

## Known Issues:
None known at the moment.  
If any are discovered, please feel free to contact me.  Cheers. :smile:
