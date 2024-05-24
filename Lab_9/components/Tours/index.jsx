import React from "react";

class Tours extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      substr: "",
      tours: []
    };

    this.handleChangeSubstr = (event) => this.handleChange(event);
  }

  handleChange = (event) => {
    const substr = event.target.value.toLowerCase();
    const tours = window.lab9models
      .toursModel()
      .filter(tour => tour.toLowerCase().includes(substr))
      .sort();

    this.setState({
      substr,
      tours
    });
  };

  render() {
    return (
      <div>
        <div className="state-search">
          {this.state.substr}
        </div>
        <div className="lab9-example-output">
          <span id='IInfo'>
            {this.state.tours.length > 0 ? (
              <p>
                {this.state.tours.map(tour => (
                  <p key={tour}>{tour}</p>
                ))}
              </p>
            ) : (
              "No tours found"
            )}
          </span>
        </div>
        <label htmlFor="substrId">Enter substring to search:</label>
        <input
          id="substrId"
          type="text"
          value={this.state.substr}
          onChange={this.handleChangeSubstr}
        />
      </div>
    );
  }
}

export default Tours;
