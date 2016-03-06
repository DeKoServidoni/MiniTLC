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
    
    // MARK: Class constants
    
    let NUMBER_OF_SECTIONS = 2
    
    let SECTION_1_HIGHT: CGFloat = 0
    let SECTION_2_HIGHT: CGFloat = 2
    
    // MARK: Table view delegate functions
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let quantitySection1 = menu.count - 2
        let quantitySection2 = menu.count - quantitySection1
        
        return (section == 0) ? quantitySection1 : quantitySection2
    }
    
    func tableView(tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return (section == 0) ? SECTION_1_HIGHT : SECTION_2_HIGHT
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return NUMBER_OF_SECTIONS
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {

        var cell = tableView.dequeueReusableCellWithIdentifier("MenuItemIdentifier") as? SlideMenuCell
        
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
       
        if indexPath.section == 0 {
            handleClickInFirstSection(onRow: indexPath.row)
        } else if indexPath.section == 1 {
            handleClickInSecondSection(onRow: indexPath.row)
        }
    }
    
    // MARK: Private functions
    
    // handle the click in the rows of the first section
    private func handleClickInFirstSection(onRow row: Int) {
        /** TODO **/
    }
    
    // handle the click in the rows of the second section
    private func handleClickInSecondSection(onRow row: Int) {
        
        switch row {
            
            case 0: /* Talk with US */
                let url = NSURL(string: "mailto:mini@tlccampinas.com.br")
                UIApplication.sharedApplication().openURL(url!)
                break
            
            default:
                // do nothing
                break
        }
    }
}