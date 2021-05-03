import React, { Component } from 'react';
import { Card, CardImg, CardImgOverlay, CardText, CardBody, CardTitle } from 'reactstrap';
import { DISHES } from '../shared/dishes';
import Dishdetail from './DishdetailComponent'

class Menu extends Component {

    constructor(props) {
        super(props);

        this.state = {
            dishes: DISHES,
			selectedDish: null
        }
    }

	onDishSelect(dish){
		this.setState({ selectedDish: dish });
	}

    renderDish(dish){
		if(dish != null){
			return(
			<div className="row">
                <div className="col-12 col-md-5  mt-1">
                    <Card>
                        <CardImg width="100%" src={dish.image} alt={dish.name} />
                        <CardBody>
                            <CardTitle>{dish.name}</CardTitle>
                            <CardText>{dish.description}</CardText>

                        </CardBody>
                    </Card>
                </div>
                <div className="col-6">
                    <Card>
                    <h4>Comments</h4>
                        {
                            dish.comments.map((comment) =>
                                <CardText>
                                    <CardText>
                                        {comment.comment}
                                    </CardText>
                                    -- {comment.author} , {new Intl.DateTimeFormat('en-US',
                                    { year: 'numeric', month: 'short', day:'2-digit'})
                                    .format(new Date(Date.parse(comment.date)))}
                                </CardText>
                            )
                        }
                    </Card>
                </div>
            </div>
		);
	}
		else {
			return(
				<div></div>
			)
		}
	}

    render() {
        const menu = this.state.dishes.map((dish) => {
            return (
              <div key={dish.id} className="col-12 col-md-5  mt-1">
                <Card onClick={() => this.onDishSelect(dish)}>
                    <CardImg top src={dish.image} alt={dish.name} width="100%"/>
                    <CardImgOverlay>
                      <CardTitle>{dish.name}</CardTitle>
                    </CardImgOverlay>
                </Card>
              </div>
            );
        });

        return (
           <Dishdetail menu={menu} selectDish={this.renderDish(this.state.selectedDish)}/>
        );
    }
}
export default Menu;