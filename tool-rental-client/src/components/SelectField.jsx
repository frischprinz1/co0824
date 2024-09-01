import { useId } from "react";

import styled from "styled-components";
import * as Label from "@radix-ui/react-label";

const SelectFieldWrapper = styled.div`
    margin-block-start: 15px;
    .LabelRoot {
        display: block;
        font-size: 13px;
        font-weight: 400;
        line-height: 19px;
        color: var(--form-subheadline-color);
    }

    .Input {
        outline: 1px solid var(--light-gray-100);

        border-radius: 2px;
        margin-block-start: 4px;
        padding-block: 10px;
        padding-inline: 15px;
        border: none;

        width: 100%;
        color: var(--form-subheadline-color);
        height: 38px;
    }
`;

const SelectField = ({ onChange, value, name, label }) => {
    const id = useId();

    //     <label for="pet-select">Choose a pet:</label>

    // <select name="pets" id="pet-select">
    //   <option value="">--Please choose an option--</option>
    //   <option value="dog">Dog</option>
    //   <option value="cat">Cat</option>
    //   <option value="hamster">Hamster</option>
    //   <option value="parrot">Parrot</option>
    //   <option value="spider">Spider</option>
    //   <option value="goldfish">Goldfish</option>
    // </select>
    // {
    //     "id": 5,
    //     "toolCode": "JAKR",
    //     "brand": {
    //         "id": 4,
    //         "name": "Ridgid",
    //         "abbreviation": "R"
    //     },
    //     "toolType": {
    //         "prefix": "JAK",
    //         "name": "Jackhammer",
    //         "rate": {
    //             "id": "JAK",
    //             "dailyCharge": 2.99,
    //             "hasWeekdayCharge": true,
    //             "hasWeekendCharge": false,
    //             "hasHolidayCharge": false
    //         }
    //     }
    // }
    return (
        <SelectFieldWrapper>
            <Label.Root
                className="LabelRoot"
                htmlFor={`${id}-${name}`}
            >
                {label}
            </Label.Root>
            <select
                name=""
                id={`${id}-${name}`}
            ></select>
            <input
                onChange={onChange}
                className="Input"
                value={value}
                type="text"
                name={name}
                id={`${id}-${name}`}
            />
        </SelectFieldWrapper>
    );
};

export default SelectField;
