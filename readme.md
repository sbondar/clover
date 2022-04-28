## Overview
The Clover App Market allows 3rd party developers to build applications that can be installed by Clover merchants.  The 3rd party developer is our primary customer on the developer platform team.  We provide a dashboard for developers to manage their applications and gain insights into their app's performance trends.
## Problem
A new feature has been requested which will allow developers to view their application's revenue in a chart (developer_revenue.png) in the dashboard.  You have been tasked with building the logic that will aggregate a list of data into a format that can be consumed by the UI.
## Input
An input data file (input.json) has been provided.  This file contains flattened revenue data for a given developer id for a one year period that was provided by the database and serialized into a JSON format.
This is the data you have to work with, you should not be storing this data into another database etc.  Use it to group/aggregate the data and build the final response. 
## Response
The UI is expecting a response in a format outlined in sampleResponse.json.  The data should be grouped by period (month) and within each month by app.  Totals for each period and app should be provided as well as a final total (for all periods/apps).

