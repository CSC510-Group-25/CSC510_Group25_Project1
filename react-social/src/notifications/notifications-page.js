import React from "react";
import Box from '@material-ui/core/Box';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import makeStyles from "@material-ui/core/styles/makeStyles";
import ResponsiveDrawer from "../navigation/drawer";

const useStyles = makeStyles({
  root: {
    minWidth: 500,
    maxWidth: 1000,
    marginTop: 90,
    marginRight: 30,
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
    <ResponsiveDrawer/>
    <Box display="flex" flexDirection="row-reverse">
      <Card className={classes.root}>
        <CardContent>
          <Typography className={classes.title} color="textSecondary" gutterBottom>
            Example Alert
          </Typography>
          <Typography variant="h5" component="h2">
            Inventory Items are about to expire!
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            Consider using up Milk, Cheese and Lettuce.
          </Typography>
        </CardContent>
      </Card>
    </Box>
  </div>
}