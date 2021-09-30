import React from "react";
import Box from '@material-ui/core/Box';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import makeStyles from "@material-ui/core/styles/makeStyles";

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

export default function NotificationsPage() {
  const classes = useStyles();

  return <div>
    <Box flexDirection="row-reverse">
      <Card className={classes.root}>
        <CardContent>
          <Typography className={classes.title} color="secondary" gutterBottom>
            Expiration Alert!
          </Typography>
          <br/>
          <Typography variant="h5" component="h2">
            It looks like some of your inventory is about to expire
          </Typography>
          <Typography variant="h6" component="h2" color="textSecondary">
            Consider using or donating your French Bread and Milk
          </Typography>
        </CardContent>
      </Card>

      <Card className={classes.root}>
        <CardContent>
          <Typography className={classes.title} color="secondary" gutterBottom>
            Low Inventory Alert!
          </Typography>
          <br/>
          <Typography variant="h5" component="h2">
            It looks like some of your inventory is getting low
          </Typography>
          <Typography variant="h6" component="h2" color="textSecondary">
            Swiss Cheese quantity is low. Try checking quantity or restricting use.
          </Typography>
        </CardContent>
      </Card>
    </Box>
  </div>
}