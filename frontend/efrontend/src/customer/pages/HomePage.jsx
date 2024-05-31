import React from 'react'
import MainCarousel from '../components/homeCarousel/MainCarousel'
import HomeSectionCarousal from '../components/homesectionCarousel/HomeSectionCarousal'

function HomePage() {
  return (
    <div>
      <MainCarousel/>
      <div>
        <HomeSectionCarousal sectionName={"Mens & Womans Collection"}/>
        <HomeSectionCarousal sectionName={"Mens Kurta's"}/>
        {/* <HomeSectionCarousal sectionName={"Mens Kurta's"}/>
        <HomeSectionCarousal sectionName={"Mens Kurta's"}/>
        <HomeSectionCarousal sectionName={"Mens Kurta's"}/> */}
      </div>
    </div>
  )
}

export default HomePage
