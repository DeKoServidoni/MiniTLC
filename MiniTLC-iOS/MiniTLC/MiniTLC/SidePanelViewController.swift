//
//  SidePanelViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/1/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

class SidePanelViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    // MARK: Table view delegate functions
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
//        let cast = fetchedCastsResultsController.objectAtIndexPath(indexPath) as! Cast
//        
//        let cell = tableView.dequeueReusableCellWithIdentifier("CastCustomCellId") as! CastCell
//        
//        cell.title.text = cast.title
//        cell.subtitle.text = cast.subtitle
//        cell.date.setFormattedDate(cast.date)
        
        return UITableViewCell()
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
       print("CLICKED!")
    }
}