import React from "react";
import Box from '@material-ui/core/Box';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import makeStyles from "@material-ui/core/styles/makeStyles";

import { useEffect } from 'react'
import { fetchAboutToExpireInventoryItems, fetchExpiredInventoryItems, fetchLowQuantityInventoryItems } from '../util/APIUtils';
import { NOTIFICATION_EXPIRY_DAYS, NOTIFICATION_LOW_QUANTITY_THRESHOLD } from '../constants';

const useStyles = makeStyles({
  root: {
    maxWidth: 800,
    marginLeft: 20,
    marginTop: 50,
    justifyContent: 'center',
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});


export default function NotificationsPage({ currentUser }) {

  const [expiredItems, setExpiredItems] = React.useState([]);
  const [aboutToExpireItems, setAboutToExpireItems] = React.useState([]);
  const [lowQuantityItems, setLowQuantityItems] = React.useState([]);

  useEffect(() => {

    if(!currentUser) {
      return;
    }
    fetchExpiredInventoryItems({
      "restaurant_name": currentUser.restaurantName,
    }).then((response) => {
      // console.log(response);
      setExpiredItems(response);
    });

    fetchAboutToExpireInventoryItems({
      "restaurant_name": currentUser.restaurantName,
      "expires_in_days": NOTIFICATION_EXPIRY_DAYS,
    }).then((response) => {
      // console.log(response);
      setAboutToExpireItems(response);
    });

    fetchLowQuantityInventoryItems({
      "restaurant_name": currentUser.restaurantName,
      "max_qty": NOTIFICATION_LOW_QUANTITY_THRESHOLD,
    }).then((response) => {
      // console.log(response);
      setLowQuantityItems(response);
    });
  }, []);

  const classes = useStyles();

  return <div>
    <Box flexDirection="row-reverse">
      {
        expiredItems.map(item => {
          return <Card className={classes.root} key={item.itemId}>
            <CardContent>
              <Typography className={classes.title} color="secondary" gutterBottom>
                Expiration Alert!
              </Typography>
              <Typography className={classes.title} color="textSecondary" gutterBottom>
                {item.dateExpired}
              </Typography>
              <Typography variant="h5" component="h2">
                {item.itemName} has expired on {item.dateExpired}
              </Typography>
              <Typography className={classes.pos} color="textSecondary">
                {item.batchQty} unit{item.batchQty > 1 ? "s have" : " has"} gone bad. Please order more.
              </Typography>
            </CardContent>
          </Card>
        })
      }
      {
        aboutToExpireItems.map(item => {
          return <Card className={classes.root} key={item.itemId}>
            <CardContent>
              <Typography className={classes.title} color="primary" gutterBottom>
                Expiration Alert!
              </Typography>
              <Typography className={classes.title} color="textSecondary" gutterBottom>
                {item.dateExpired}
              </Typography>
              <Typography variant="h5" component="h2">
                {item.itemName} is about to expire on {item.dateExpired}
              </Typography>
              <Typography className={classes.pos} color="textSecondary">
                {item.batchQty} unit{item.batchQty > 1 ? "s are" : " is"} about to expire. Order replacement soon!
              </Typography>
            </CardContent>
          </Card>
        })
      }
      {
        lowQuantityItems.map(item => {
          return <Card className={classes.root} key={item.itemId}>
            <CardContent>
              <Typography className={classes.title} color="secondary" gutterBottom>
                Low Inventory Alert!
              </Typography>
              <Typography className={classes.title} color="textSecondary" gutterBottom>
                Only {item.batchQty} unit{item.batchQty > 1 ? "s are" : " is"} left in stock.
              </Typography>
              <Typography variant="h5" component="h2">
                It looks like some of your inventory is getting low
              </Typography>
              <Typography variant="h6" component="h2" color="textSecondary">
                {item.itemName} quantity is low. Try checking quantity or restricting use.
              </Typography>
            </CardContent>
          </Card>
        })
      }
    </Box>
  </div>
}