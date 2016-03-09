//
//  SidePanelViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/1/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

protocol SidePanelViewControllerDelegate {
    func openViewController(controller: UIViewController?)
}

class SidePanelViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    // MARK: Class attributes
    
    var menuSection0: [String] = ["Início"]
    var menuSection1: [String] = ["Tirar foto!"]
    var menuSection2: [String] = ["Fale conosco!", "Sobre"]
    
    var iconSection0: [String] = ["icon_home"]
    var iconSection1: [String] = ["icon_camera"]
    var iconSection2: [String] = ["icon_talk_with_us", "icon_about"]
    
    var delegate: SidePanelViewControllerDelegate?
    
    var loadedStoryboard: UIStoryboard?
    
    // MARK: Class constants
    
    let NUMBER_OF_SECTIONS = 3
    
    // MARK: Lifecycle functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadedStoryboard = UIStoryboard(name: "Main", bundle: NSBundle.mainBundle())
    }

    
    // MARK: Table view delegate functions
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        var quantity = menuSection0.count
        
        if section == 1 {
            quantity = menuSection1.count
        } else if section == 2 {
            quantity = menuSection2.count
        }
        
        return quantity
    }
    
    func tableView(tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return (section == 0) ? 0 : 2
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
        
        var itemText = ""
        var itemImage = ""
        
        if indexPath.section == 0 {
            itemImage = iconSection0[indexPath.row]
            itemText = menuSection0[indexPath.row]
        
        } else if indexPath.section == 1 {
            itemImage = iconSection1[indexPath.row]
            itemText = menuSection1[indexPath.row]
            
        } else {
            itemImage = iconSection2[indexPath.row]
            itemText = menuSection2[indexPath.row]
        }

        cell!.icon.image = UIImage(named: itemImage)
        cell!.label.text = itemText
        
        return cell!
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        var controller: UIViewController?
        
        switch indexPath.row {
            
            case 0: /** Home - Picture chooser - Talk with Us **/
                if indexPath.section == 0 {
                    controller = loadedStoryboard!.instantiateViewControllerWithIdentifier("HomeViewController") as? HomeViewController
                } else if indexPath.section == 1 {
                    controller = loadedStoryboard!.instantiateViewControllerWithIdentifier("PictureChooserViewController") as? PictureChooserViewController
                } else {
                    let url = NSURL(string: "mailto:mini@tlccampinas.com.br")
                    UIApplication.sharedApplication().openURL(url!)
                }
                break
         
            case 1:
                break
            
            default:
                // do nothing
                break
        }
        
        delegate?.openViewController(controller)
    }
}