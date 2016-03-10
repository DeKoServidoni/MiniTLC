//
//  HomeViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/9/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

class HomeViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    // MARK: Class attributes
    
    var events: [Event] = []
    
    // MARK: Lifecycle functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let filePath = NSBundle.mainBundle().pathForResource("document",ofType:"json")
        
        if let data = NSData(contentsOfFile: filePath!) {
            
            do {
                var json = try NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions()) as! [String: AnyObject]
            
                for item in json["events"] as! [[String:AnyObject]] {
                    
                    let event = Event()
                    event.title = item["title"] as! String
                    event.description = item["description"] as! String
                    event.date = item["date"] as! String
                    
                    events.append(event)
                }
                
            } catch {
                print(error)
            }
        }
    }
    
    // MARK: Table view delegate functions
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return events.count
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        return 110
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let event = events[indexPath.row]
        var cell = tableView.dequeueReusableCellWithIdentifier("HomeItemIdentifier") as? HomeCell
        
        if cell == nil {
            let array = NSBundle.mainBundle().loadNibNamed("HomeCell", owner: self, options: nil)
            cell = array[0] as? HomeCell
        }
        
        cell!.title.text = event.title
        cell!.date.text = event.date
        cell!.desc.text = event.description
        
        return cell!
    }
}