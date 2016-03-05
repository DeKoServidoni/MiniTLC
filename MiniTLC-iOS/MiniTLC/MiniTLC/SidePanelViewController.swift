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
    
    // MARK: Class attributes
    var menu: [String] = ["Tirar foto!","Fale conosco!", "Sobre"]
    var icon: [String] = ["icon_camera", "icon_talk_with_us", "icon_about"]
    
    let NUMBER_OF_SECTIONS = 2
    
    // MARK: Table view delegate functions
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (section == 0) ? 1 : 2
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return (section == 1) ? "TESTE" : ""
    }
    
    //func tableView(tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        //
    //}
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return NUMBER_OF_SECTIONS
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let identifier = "MenuItemIdentifier"
        
        var cell = tableView.dequeueReusableCellWithIdentifier(identifier) as? SlideMenuCell
        
        if cell == nil {
            let array = NSBundle.mainBundle().loadNibNamed("SlideMenuCell", owner: self, options: nil)
            cell = array[0] as? SlideMenuCell
        }
        
        let index = (indexPath.section == 1) ? indexPath.row + 1 : indexPath.row

        cell!.icon.image = UIImage(named: icon[index])
        cell!.label.text = menu[index]
        
        return cell!
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
       print("CLICKED!")
    }
}